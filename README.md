# CommandLineEncryptionDecryption
This is a program that encrypts/decrypts a text input/file using the arguments passed to it on the command line

## Getting Started

Download the project repo ZIP and extract it.

Extract the ZIP file.
### Prerequisites

```
java (version 13.0.2)
```


## Running the tests


Open the Command Line or Terminal and go to the src directory of the project.

Then run the program while passing the required arguments.

###Input Arguments
* Key - This is the number of movements the text has to shift by. Default - 0 
* Algorithm - This is the encrypting/decrypting algorithm the program is going to make use of.
 They include the Shift and Unicode algorithms. Default - Shift Algorithm
* Data - This is the text input in the form of a string that is to be encrypted or decrypted.
* mode - This the the instruction. "enc" for encryption and "dec" for decryption. Default - "enc"

### Break down into end to end tests

Explain what these tests test and why

```
java Main -key 5 -alg shift -data "Hello, this is a test." -mode enc
Output- Mjqqt, ymnx nx f yjxy.
```
```
java Main -key 5 -alg shift -data "Mjqqt, ymnx nx f yjxy." -mode dec
Output- Hello, this is a test.
```

## Built With

* Java 13.0.2 - The programming language used.

## Authors

* **Oluwatobi Olutimehin** 




