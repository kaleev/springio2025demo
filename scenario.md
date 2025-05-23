# TASK – “ADD RANDOM CUSTOMER” HANGS, THEN DUPLICATES

## Goal  
The **“Add Random Customer”** button on the home page must add exactly **one** customer with a **random** name every time it is pressed.

There are three independent defects to fix:

1. The first click never returns – the request is stuck in a nested transaction started in `TransactionLevel5Service`.
2. Spring wires **MockTransactionLevel4Service** instead of the real `DemoTransactionLevel4Service`, causing an extra database call.
3. Property **user.random.names** resolves to `false`, so fixed names are used even after the hang is gone.

Follow the steps below to verify and remove each defect.

---

## Step-by-Step Instructions

### Step 1: Reproduce the Hang
1. Start the application and press **Add Random Customer** once.  
   - The browser keeps loading – you will reproduce the hang.

2. Set a breakpoint on the first line of  
   `CustomerService#createRandomCustomer(...)`, restart the application, and repeat the click.  
   - You should reach the breakpoint **twice**:
     - The first hit originates from *TransactionLevel1Service* (outer transaction).
     - The second hit originates from *TransactionLevel5Service* (inner transaction).  
   - Inspect the call stack on the second hit to confirm that the inner transaction (`Propagation.REQUIRES_NEW`) is waiting for a lock held by the outer one.

---

### Step 2: Remove Custom Propagation
1. Open **TransactionLevel5Service**.
2. Remove the custom propagation:
   - Delete `propagation = Propagation.REQUIRES_NEW`.
   - Retain the default `@Transactional` annotation only.
3. Restart the application.

---

### Step 3: Verify Correct Service Injection
1. Open **TransactionLevel3Service**:
   - Ensure it injects the correct `TransactionLevel4Service`.
   - Set a breakpoint at `TransactionLevel3Service#processTransactionLevel3(...)`.
   - Restrict the mock service to the *test* profile only.
2. Restart the application.

---

### Step 4: Address Duplicate Names
1. Restart the application.  
   - Click the button again – the request now returns, but the second click fails with a “duplicate username” error.

2. During debugging, you will notice that the flag `useRandomNames` is `false`.  
   - Locate **user.random.names** in the property files and set it to `true` for the active profile (e.g., in `application-demo.properties`).

---

### Step 5: Final Test
1. Restart the application.
2. Press **Add Random Customer** a few times:  
   - The page returns immediately.  
   - Each click inserts one customer.  
   - Names are different every time.

---

## Results  
🎉 You’re done! The button now works as expected:
- One random customer is added per click.
- The page no longer hangs.
- Each customer gets a unique random name.

---