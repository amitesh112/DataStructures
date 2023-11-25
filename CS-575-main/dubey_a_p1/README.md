# CS-575

### Author
### Amitesh Dubey - adubey1@binghamton.edu

-----------------------------------------------------------------------
-----------------------------------------------------------------------
## Programming Language- Java
## Build System-ANT(Available on remote.cs)

-----------------------------------------------------------------------
## ANT Commands 
 **Note: build.xml is present in bruteForceMaxProfit/src folder.**

 **IMPORTANT! : Run all the following commands from the ~/dubey_a_p1/ directory**

#### Instruction to clean:

 **Command: ant -buildfile bruteForceMaxProfit/src/build.xml clean**
Description: It cleans up all the .class files that were generated when you
compiled your code.

#### Instruction to compile:

 **Command: ant -buildfile bruteForceMaxProfit/src/build.xml all**

Description: Compiles your code and generates .class files inside the BUILD folder.

#### Instruction to run:
 **Command: ant -buildfile bruteForceMaxProfit/src/build.xml run -Darg0=-m -Darg1=market_price.txt -Darg2=-p -Darg3=price_list.txt**
or the following command if -p file is provided before
 **Command: ant -buildfile bruteForceMaxProfit/src/build.xml run -Darg0=-p -Darg1=price_list.txt -Dagr2=-m -Darg3=market_price.txt**

Format: Input file kind, Input File, Input file kind, Input File

Note : all the input/output files are expected to be at the level of the src/ directory. Example:
bruteForceMaxProfit/src
bruteForceMaxProfit/market_price.txt

-----------------------------------------------------------------------