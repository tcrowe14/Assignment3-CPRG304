Assignment3CPRG304

BST Data Structure and Word Tracker

The WordTracker.jar file will take a local text file or filepath and run it through the Word Tracker program 
using our own defined libraries for Binary Search Trees, tracking words, line numbers, and file names
and storing in a local repository. The Program checks the repository and either creates or adds words. Reports can be viewed as a print out on screen after the Word Tracker has run its course.

Usage:

There are three mutually exclusive options at the command line:  
• -pf prints in alphabetic order all words, along with the corresponding list of files 
in which the words occur.  
• -pl prints in alphabetic order all words, along with the corresponding list of files 
and line numbers in which the word occur.  
• -po prints in alphabetic order all words, along with the corresponding list of files, 
line numbers in which the word occur and the frequency of occurrence of the 
words.  
• An optional argument to redirect the report in the previous step to the path and 
filename specified in <output.txt>.  

Example Use:

    java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f<output.txt>]