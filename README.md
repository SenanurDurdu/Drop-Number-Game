# Number Drop Game - Multi Linked List Implementation

## Overview

This project is a Java-based implementation of a Number Drop Game using a **Multi-Linked List** data structure. The objective of the game is to drop numbered tiles into a 7x5 grid and merge tiles with the same value to create larger numbers.

The project was developed as a Data Structures assignment with the main requirement of avoiding traditional 2D array representations and instead managing the entire grid through linked structures.

A Java Swing graphical user interface (GUI) is included to visualize the game step-by-step.

## Features

* 7x5 game grid
* Multi-Linked List based grid representation
* No array-based game storage
* Vertical number dropping
* Automatic neighbor linking
* Recursive merge operations
* Swing GUI visualization
* Color-coded tiles
* Sequential number generation using a linked list

## Data Structures

### Node

Each tile in the grid is represented by a Node containing:

* Value
* Row position
* Column position
* Up pointer
* Down pointer
* Left pointer
* Right pointer
* Next pointer

The directional pointers provide navigation inside the grid while the next pointer allows traversal of all nodes from a single head reference.

### MultiLinkedList

The MultiLinkedList class manages:

* Node insertion
* Node removal
* Neighbor connections
* Merge operations
* Grid traversal
* Grid printing

### NumberNode

A separate singly linked list is used to store the predefined sequence of numbers.

This structure enables sequential access without relying on indexing operations.

## Game Logic

For every step:

1. Read the next number from the NumberNode list.
2. Determine the target column.
3. Find the lowest available row in that column.
4. Create a new node.
5. Add the node to the MultiLinkedList.
6. Connect neighboring nodes.
7. Check merge conditions.
8. Update the GUI.

## Merge Mechanism

When a newly inserted node has the same value as the node directly below it:

1. The lower node's value is doubled.
2. The upper node is removed.
3. Neighbor pointers are updated.
4. Recursive merge checking continues until no additional merge is possible.

Example:

2

2

↓

4

## Graphical User Interface

The project includes a Java Swing interface that:

* Displays the 7x5 grid
* Updates after every move
* Shows tile values
* Uses different colors for different tile values
* Simulates the scenario using a button click

## Technologies Used

* Java
* Java Swing
* Object-Oriented Programming
* Linked Lists
* Multi-Linked List Structure

## Project Structure

* Node.java → Grid node structure
* NumberNode.java → Number sequence linked list
* MultiLinkedList.java → Core game logic
* DropNumberFrame.java → GUI implementation

## Educational Purpose

This project demonstrates:

* Multi-linked list design
* Pointer-based navigation
* Dynamic memory management
* Recursive algorithms
* GUI integration with data structures

## Author

Developed by Sena as a Data Structures course project.
