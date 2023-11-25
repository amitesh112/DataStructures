# CS-575

### Author

### Amitesh Dubey - adubey1@binghamton.edu

---

## Programming Language: Java

---

**IMPORTANT! : Run all the following commands from the ~/dubey_a_p3/ directory**

#### Instruction to clean:

**Command: make clean**  
Description: It cleans up all the .class files that were generated when you
compiled your code.

#### Instruction to compile:

 **Command: make**

Description: Compiles your code and generates .class files.

#### Instruction to run:
Longest Common Sequence:
 **Command: java LcsDriver arg1 arg2**

Floyd:
 **Command: java FlydDriver**

Note : all the input/output files are expected to be at the level of the src/ directory. Example:
$ make
$ java LcsDriver abc afgbhcd
Length of LCS: 3
LCS:abc
 
$ java FloydDriver
----------------------------------
Random Number Selected is 5
AMatrix:
 0 8 7 9 4
 8 0 8 8 3
 7 8 0 3 7
 9 8 3 0 1
 4 3 7 1 0

PMatrix:
0 5 0 5 0 
5 0 5 5 0 
0 5 0 0 4 
5 5 0 0 0 
0 0 4 0 0  

V1 - Vj: Shortest path length
V1 V1: 0
V1 V5 V2: 7
V1 V3: 7
V1 V5 V4: 5
V1 V5: 4

V2 - Vj: Shortest path length
V2 V5 V1: 7
V2 V2: 0
V2 V5 V4 V3: 7
V2 V5 V4: 4
V2 V5: 3

V3 - Vj: Shortest path length
V3 V1: 7
V3 V4 V5 V2: 7
V3 V3: 0
V3 V4: 3
V3 V4 V5: 4

V4 - Vj: Shortest path length
V4 V5 V1: 5
V4 V5 V2: 4
V4 V3: 3
V4 V4: 0
V4 V5: 1

V5 - Vj: Shortest path length
V5 V1: 4
V5 V2: 3
V5 V4 V3: 4
V5 V4: 1
V5 V5: 0
-----------------------------------------------------------------------
