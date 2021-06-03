# \title{LaTex Converter}
## Table of Contents
1. [General Info](#general-info)
2. [How to run the program](#how-to-run-the-program)
3. [How it works](#how-it-works)
4. [Instructions](#instructions)

## General Info
***
Our project is a LaTex converter, this means that we can convert every mathematical expression into a Latex code. This program allows the user to convert simple operation (Addition, Subtraction, etc.) and some more complex operation as Limit, Root and Power and then add results in a .tex file. In addition there's the possibility to add strings inside the .tex file that the user create.

## How to run the program
***
TODO
```
$ something here
$ something here
$ something here
```

## How it works
***
Once we start the program, a window will open where we will find the GUI of our project that will manage the user's requests and is divided into two parts: the input area, at the top, and the black background output area.
### Input area
On the input area we'll find the following element:
- **"Help" button**: ???
- A **text field** where the user can write his math expression and using a correct syntax even strings.
- A **"Convert" button** which converts what's in the text field.
- An **"Add" button** which is used to add the code that we converted into a .tex file.
### Output area
The output area is an entire space where the user can see the result of the conversion.

## Instructions
***
For a correct execution of the program we developers have created some simple grammar rules to be applied for the creation of the mathematical expressions that we list below.
- **Simple operations**: there aren't specific rule, but you have to use correctly the parenthesis. (eg. *((5+7)/(7-3))* ).
- **Equations** follow this rule, *(first_expression) = (second_expression)*. (eg. *(5+3) = (4+4)*).
- **Power**: follow this rule, (base)^(exponent), if and only if the exponent is a single number **DON'T USE** parenthesis for the exponent. (eg. (5+1)^(3*2), (5+1)^2 ).
- **Limit**: follow this rule, *lim:(variable,value):(expression):*. (eg. *lim:(x,10):((x+3)/5):* ).
- **Root**: follow this rule, *root:grade:(expression):*. (eg. *root:2:(5+3):* ).
- **String**: for strings you have two possibilities, you can print only a string writing *string(sentence_to_add)* or you can directly print a string with an expression after separeted by a colon in this way *string(sentence_to_add):(expression)*. (eg. *string(Hello World)*, *string(Commutative Property):(a+b) = (b+a)* ).
