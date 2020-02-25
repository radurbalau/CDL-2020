# CDL-2020
Open Source Search Engine

### Prerequisites
To install the software you need to install a version of IntelliJ IDEA **OR**
install java 11 with my installation script.

### Installing
Run with sudo the installation bash script with the command:
```
  sudo ./installation.sh
```
It will install JDK 11 needed to run this application 
  
### Running the tests
-Open IntelliJ IDEA and click on File->Open and search where you downloaded the project
**OR**
 If you do not have IntelliJ IDEA installed, navigate to the /src/com directory
and run the commands:
```
javac -g company/Main.java
```
```
java company/Main.java
```

-Once you run the project a pop up window will appear, that is the GUI. Type in the first box
what combination of words you are searching for.
  The inputs may look like:
  ```
  Canada || Europe
  ```
  ```
  open && !(source) && (security || C)
  ```
  ```
  !Linus
  ```
  The output consists of the file's number that respects the condition from input.
  For example if there are 3 files. And the firs and the last one respect the condition from imput,
    but the second one does not respect them the output will look like:
    "1 3".
  
## Built With 
  [Java] - version 11.0.5
  
## Author
  *Durbalau Radu - Andrei

###
