# ds_project_1
This is the first project for the cource Data Structures at AUEB fot the year 2019-2020.

In this project, we implemented basic data structures and the Thiseas maze game.

## Basic data structures

* List node
* List
* Circle list
* String queue
* String stack

All the above have been implemented with Generics.

## Thiseas maze 

Implementation of a backtracking game using the stack from the basic data structures that we previously define.

Main goal: manage to find a way and exit the maze

> By exit we mean that we have to find a path from the entering point that reaches the table borders.

These are the five main symbols that we use:

E: entering point

0: accessible path

1: deadlock path

G: goal points (points that are 0 in the table borders)

V: visited path

Example:

| 1 | 1 | 1 | E | 1 | 1 | 1 |
|---|---|---|---|---|---|---|
| 1 | 1 | 1 | 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 | 1 | 0 | 1 |
| 1 | 0 | 1 | 0 | 1 | 0 | 0 |
| 1 | 1 | 1 | 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 | 0 | 0 | 1 |
| 1 | 0 | 1 | 1 | 1 | 0 | 1 |
| 1 | 0 | 1 | 1 | 0 | 0 | 1 |
| 0 | 1 | 1 | 1 | 0 | 1 | 1 |


**For more information, have a look at the pdf file. All the files contain comments that help someone understand the code quite precisely.**