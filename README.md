# AVLTree
AVL Tree implementation in Java for a college homework. 

The java file is an implementation of an AVL Tree based solution for a college homework. 

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------#

Problem:

Build a java program that generates a reference list for a given text.
The program is prepared to receive a text which starts with the string "TEXTO" in the first line of the file so it can store every diferent word of the input text
and the lines in which the word occur. This happens until the string "FIM" is received by the reader.

Every word is now stored in a data structure (AVL Tree in this case) and ready to be searched. 

After "FIM", the text file must have the following commands:
"LINHAS" (word) -> This gives the lines of the text in which the searched word occur.
"ASSOC" (word) (line) -> This gives the result "ENCONTRADA" if the word is on that line or "NAO ENCONTRADA" if otherwise.

Notes: The text file in the repository is in PORTUGUESE. Almost all the code is in english. 

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------#


Compile and Run specifications:

  Compilation:
    javac A1.java

  Running:
    java A1.java < input_1.txt
