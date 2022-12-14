# coding-assessment

Coding Assessment for Deutsche Bank. The solution has been implemented as a spring boot application.

## Problem

We would like you to make any changes to your teams code to make the code:

*	Easier for your team to understand and debug
*	Easier for your team to maintain and add new signals
*	Easier to test
    *	The code should have appropriate levels of testing to ensure that the stated requirements are met.

The code should be a running service with a single http endpoint for receiving the ‘signal’ which will then be passed through the ‘SignalHandler’ interface and onto your application.

## Solution

Instead of storing the 'signals' in the java class and thereby decreasing readability we extract the signals into a database, where maintenance is easier. When a new signal is processed, we 
1. find the corresponding action sequence in the database
2. deserialize the sequence as a list of key value pairs, where each key is a sequence step and each value is the corresponding parameters
3. execute the sequence by matching the key with the corresponding method from the trading algo library

### Advantages:

* New sequences can be added to the database during application runtime
* Increased readability of the code

### Disadvantages

* New trading algo library methods require code changes (albeit small)

## Usage

The spring boot application can be started from the class [CodingAssessmentApplication](https://github.com/samakk/coding-assessment/blob/3835c0a6ea83220db437aad4b59c7f4cb482c599/src/main/java/com/krisksama/codingassessment/CodingAssessmentApplication.java). After the application has started, one can make requests with [requests.http](https://github.com/samakk/coding-assessment/blob/3835c0a6ea83220db437aad4b59c7f4cb482c599/requests.http).