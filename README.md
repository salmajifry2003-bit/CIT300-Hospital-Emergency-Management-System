# Mini Hospital Emergency Management System

A console-based Java application built for **CIT300 - Data Structures and Algorithms**
(Individual Mid Assignment). It simulates hospital emergency operations using four
core data structures.

## Data Structures Used

| Structure | Class | Purpose |
|---|---|---|
| Binary Search Tree | `PatientBST.java` | Stores all patients, keyed by Patient ID. Supports insert, search, delete, in-order traversal. |
| Queue | `EmergencyQueue.java` | Manages patients waiting for emergency treatment (FIFO). |
| Stack | `TreatmentStack.java` | Stores completed treatment records (LIFO). |
| Singly Linked List | `VisitHistory.java` | Stores each patient's past visit history. |

## Project Structure

## How to Run
1. Make sure Java (JDK 17+) is installed.
2. Open a terminal inside the `src` folder.
3. Compile: `javac *.java`
4. Run: `java Main`
5. Use the on-screen menu to register patients, manage the emergency queue,
   complete treatments, and view visit history.

## Features
- **Patient Records (BST):** insert, search by ID, delete, in-order traversal (ascending Patient ID)
- **Emergency Queue:** enqueue, dequeue, display waiting patients, empty-queue handling
- **Treatment History (Stack):** push completed treatments, display records, empty-stack handling
- **Patient Visit History (Singly Linked List):** add visit, remove visit, search visit, display all visits — one linked list per patient

## Design Decisions
- The BST is keyed by `patientId` so lookups, inserts, and deletes run in
  O(log n) on average.
- The Queue and Stack are implemented manually with linked nodes (not
  `java.util.Queue`/`Stack`) to demonstrate understanding of the underlying
  structure rather than relying on built-in classes.
- Each `Patient` owns its own `VisitHistory` linked list, so visit records
  stay tied to the correct patient even as more patients are added.
- Completing a queued treatment automatically pushes a record onto the
  `TreatmentStack` **and** appends a `Visit` to that patient's history,
  keeping all four structures consistent with each other.

## Author
Salma Jifry — 23DA2-0654