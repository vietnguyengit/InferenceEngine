
 # AI Inference Engine
 
 :warning: **Copying this repo for university related works is prohibited.**
 
 ### Author:
- Hoang Viet Nguyen
- Artificial Intelligence - Propositional Logic


### I. INSTRUCTION:

Notice: test files must be placed into the same folder level of the iengine file

a) If you are using Mac OS or Linux (unix executable extension):

- Step 1: use cd command to change working directory to the "release" folder
after unzipping the zip file. Put test files into "release" folder as well.

E.g.: `cd ~/Dekstop/iengine/release`

- Step 2: use the command format below

`./iengine method filename`

Where:

- method is either TT or BC or FC
- filename is the full name of text file contains information

E.g.: `./iengine BC test.txt`


b) If you are using Windows (*.exe extension)

- Step 1: same above

- Step 2: almost same above; however you don't have to type ./ before the executable file

E.g.: `iengine FC test.txt`


c) If you want to run the program using *.bat file (Only when you are using Windows)

- Step 1: after unzipped the zip file, instead of changing working directory to
"release" folder, you will need to go for the "source_code" folder. Put test files
into "source_code" as well.


E.g.: `cd ~/Dekstop/iengine/source_code`

- Step 2: same above.


### II. FEATURES/BUGS:

a) This project is capable to solve Horn-form knowledge bases using 3 different algorithms:

1. Truth Table (TT): the program will answer whether the query is entailed by the KB.
It also display the number of models where the KB is true and the query is also true.

2. Forward Chaining (FC): the program will answer whether the query is entailed by the KB and
displaying the list of propositional symbols entailed from the KB which has been explored during
the execution.

3. Backward Chaining (BC): similar to FC but in the reverse direction when solving the problem.
The list of discovered entailed symbols during the execution when using BC is fewer than using FC.

b) The project can handle all common errors when executing the command and displaying appropriate
messages to acknowledge user what was wrong. Such as incorrect command, file not found, incorrect method
keyword, incorrect file name etc.

c) The project was exported into executable files such as Unix executable file (Linux, Mac OS), *.exe and *.bat.
That means the project can be published to the public without having to give the source code (unless using *.bat).
It can be tested by multi operating systems.

d) No bugs so far with the given test cases and test case built from Figure 7.16 page 259 (AI - A Modern Approach 3rd).
The program was able to return expected results.

e) All the necessary requirements of this assignment have been implemented.


### III. TEST CASES:


a) Case 1 (*** No bugs found in any algorithms***)

```
TELL
p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;
ASK
d
```

Results:

```
1. BC: 
	YES: p2, p3, p1, d

2. FC:
	YES: a, b, p2, p3, p1, d

3. TT:
	YES: 3
```

a) Case 2 - This case was built using information from Figure 7.16 page 259 of the text book
(*** No bugs found in any algorithms***)

```
TELL
p => q; l&m => p; b&l => m; a&p=>l; a&b => l; a; b;
ASK
q
```

Results:

```
1. BC: 
	YES: a, l, p, q

2. FC:
	YES: a, b, l, m, p, q

3. TT:
	YES: 1
```
	
