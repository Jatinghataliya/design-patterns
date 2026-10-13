# Object Relationships in Java

## What Is a Relationship?

In object-oriented programming a **relationship** describes how two classes know about,
use, or own each other.  
Understanding relationships helps you design systems where objects collaborate correctly,
have the right lifecycles, and are easy to change independently.

There are three fundamental relationship types covered here:

| Relationship | Keyword    | Lifecycle dependency     | UML symbol      |
|-------------|------------|--------------------------|-----------------|
| Association | "uses"     | Independent              | `A ──── B`      |
| Aggregation | "has-a"    | Part survives the whole  | `A ◇──── B`     |
| Composition | "owns"     | Part dies with the whole | `A ◆──── B`     |

---

## 1. Association

### Definition
Association is the **loosest relationship**.  
Class A simply **knows about** or **uses** Class B.  
Neither object owns the other, and both have completely independent lifecycles.

### Real-world analogy
A **Student** and a **Teacher** have an association.  
They interact (the student learns from the teacher), but either can exist, be replaced,
or be destroyed without affecting the other.

### When to use
- When two objects need to communicate but do not own each other.
- When the relationship is temporary or context-dependent (e.g., passed as a method argument).

### Example
```
src/relationship/association/
  Student.java   — calls teacher.getName() but does not hold a permanent reference
  Teacher.java   — exists independently
```

```java
Teacher teacher = new Teacher("Mr. Sharma");
Student student = new Student("Amit");
student.learnFrom(teacher);   // student "uses" teacher — pure association
```

---

## 2. Aggregation

### Definition
Aggregation is a **"whole-part"** relationship.  
Class A (the whole) **contains** Class B (the part), but **B can exist without A**.  
The part is created outside the whole and passed in, so it survives if the whole is destroyed.

### Real-world analogy
A **Department** has **Professors**.  
If the Computer Science department is shut down, the professors still exist — they can
move to another department or the university.

### When to use
- When objects form a logical group but the members have their own independent existence.
- When the "part" objects can be shared between multiple "whole" objects.

### Example
```
src/relationship/aggregation/
  Department.java   — holds a List<Professor>; professors are passed in from outside
  Professor.java    — created and managed independently
```

```java
Professor p1 = new Professor("Dr. Rao");
Department cs = new Department("Computer Science");
cs.addProfessor(p1);           // Department contains Professor
cs = null;                     // Department gone — Dr. Rao still lives
System.out.println(p1.getName()); // works fine
```

---

## 3. Composition

### Definition
Composition is the **strongest relationship**.  
Class A (the whole) **owns** Class B (the part) entirely.  
The part is **created inside the whole** and **cannot exist without it**.  
When the whole is destroyed, its parts are destroyed with it.

### Real-world analogy
A **House** has **Rooms**.  
A Room has no meaning outside a specific House; it is created when the House is built
and demolished when the House is torn down.

### When to use
- When the part has no logical existence outside the whole.
- When you want to enforce that the lifecycle of a part is fully controlled by the whole.
- Strong encapsulation — the whole is solely responsible for its parts.

### Example
```
src/relationship/composition/
  House.java   — creates Room objects internally; they are never exposed outside
  Room.java    — meaningful only within a House
```

```java
House house = new House("12 Main Street", "Bedroom", "Kitchen", "Bathroom");
house.printRooms();
house = null; // Rooms are gone — no outside reference to them exists
```

---

## Key Differences at a Glance

| Feature                   | Association         | Aggregation          | Composition           |
|---------------------------|---------------------|----------------------|-----------------------|
| Type of bond              | Uses / knows        | Has-a (weak)         | Owns (strong)         |
| Lifecycle coupling        | None                | Part survives whole  | Part dies with whole  |
| Created by                | Outside both        | Outside the whole    | Inside the whole      |
| Can be shared?            | Yes                 | Yes                  | No                    |
| UML                       | Simple line (`──`)  | Hollow diamond (`◇`) | Filled diamond (`◆`)  |

---

## Purpose of Studying Relationships

1. **Correct modelling** — you pick the right relationship and avoid bugs caused by
   objects being destroyed too early or outliving their purpose.
2. **Loose coupling** — Association and Aggregation keep classes independent so they
   are easier to test and reuse.
3. **Encapsulation** — Composition hides internal parts behind the owning class,
   preventing external code from interfering.
4. **Better maintenance** — clear ownership rules tell every developer who is responsible
   for creating, sharing, and destroying an object.

---

## Project Structure

```
src/relationship/
├── README.md
├── RelationshipDemo.java          ← main runner (shows all three in one run)
├── association/
│   ├── Student.java               ← uses a Teacher (association)
│   └── Teacher.java
├── aggregation/
│   ├── Department.java            ← has Professors (aggregation)
│   └── Professor.java
└── composition/
    ├── House.java                 ← owns Rooms (composition)
    └── Room.java
```

## How to Run

```bash
# Compile from project root
javac -d out src/relationship/association/*.java \
             src/relationship/aggregation/*.java \
             src/relationship/composition/*.java \
             src/relationship/RelationshipDemo.java

# Run
java -cp out relationship.RelationshipDemo
```

### Expected Output

```
=== Association (Student uses Teacher) ===
Amit is learning from Mr. Sharma
Teacher still exists: Mr. Sharma

=== Aggregation (Department has Professors) ===
Department Computer Science has:
  - Dr. Rao
  - Dr. Mehta
Professors still exist: Dr. Rao, Dr. Mehta

=== Composition (House owns Rooms) ===
House at 12 Main Street has rooms:
  - Bedroom
  - Kitchen
  - Bathroom
House destroyed -> its Rooms are destroyed too
```
