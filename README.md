# Creating an Intelligent Agent Capable of Playing Connect 4 with Minimax Algorithm

The goal of this project was to develop an intelligent agent capable of playing a version of the game "Connect Four" (also known as Four in a Row, Drop Four, etc.). The minimax algorithm with a depth cut was used to develop the agent. An optimized version of the agent was also developed by making use of alpha-beta pruning.

You can find 4 different subdirectories in this project, that is, `minmax`, `minmax.alphabeta`, `minmax.simple` and `minmax.alphabeta.simple`. Those that contain 'alphabeta' in the name are implementations of the minimax algorithm that make use of alpha-beta pruning optimization technique. The ones that have 'simple' in the name have a simple utility function to evaluate a state, which returns 1 or -1 in case of a victory and 0 in all other cases. The subdirectories that don't have 'simple' in their names are making use of a more sophisticated evaluation function, which gives a punctuation also for non-terminal states based on the likelihood of a victory for the current player.

## How to run

1. Make sure you have JDK installed, navigate to one of the subdirectories of the project, depending on which version of the program you want to execute, compile all the classes and execute the main function:

```
javac *.java && java Main
```

## Tests & Statistics / Benchmarking

In each subdirectory there is a `Tests.java` file which contain tests of the algorithm with 10 different pre-filled configurations of the board. You can check the number of visited nodes and the time the algorithm took to find the optimal solutions for each configuration. All you need to do is to compile and execute the Tests program:

```
javac *.java && java Tests
```

### Expanded Nodes (search depth = 5)

| States   | Minimax w/ simple utility function | Minimax alpha-beta w/ simple utility function | Minimax w/ evaluation function | Minimax alpha-beta w/ evaluation function |
| -------- | ---------------------------------- | --------------------------------------------- | ------------------------------ | ----------------------------------------- |
| Board 1  | 440 131                            | 7 981                                         | 676 620                        | 14 916                                    |
| Board 2  | 14 843 390                         | 69 779                                        | 74 832 086                     | 102 787                                   |
| Board 3  | 396 076                            | 2 926                                         | 396 076                        | 10 151                                    |
| Board 4  | 804 050                            | 4 336                                         | 804 050                        | 4 336                                     |
| Board 5  | 266 645                            | 9 023                                         | 681 891                        | 10 817                                    |
| Board 6  | 1 106 821                          | 5 185                                         | 1 106 821                      | 5 185                                     |
| Board 7  | 1 106 821                          | 5 185                                         | 1 106 821                      | 5 185                                     |
| Board 8  | 17 783 701                         | 25 201                                        | 17 783 701                     | 25 201                                    |
| Board 9  | 1 635 352                          | 6 987                                         | 967 533                        | 7 257                                     |
| Board 10 | overflow                           | 1 212 485                                     | overflow                       | 1 820 004                                 |

### Execution Time (search depth = 5)

| States   | Minimax w/ simple utility function | Minimax alpha-beta w/ simple utility function | Minimax w/ evaluation function | Minimax alpha-beta w/ evaluation function |
| -------- | ---------------------------------- | --------------------------------------------- | ------------------------------ | ----------------------------------------- |
| Board 1  | 789 ms                             | 19 ms                                         | 433 ms                         | 31 ms                                     |
| Board 2  | 34571 ms                           | 96 ms                                         | 38844 ms                       | 83 ms                                     |
| Board 3  | 286 ms                             | 2 ms                                          | 194 ms                         | 7 ms                                      |
| Board 4  | 601 ms                             | 3 ms                                          | 408 ms                         | 4 ms                                      |
| Board 5  | 289 ms                             | 6 ms                                          | 352 ms                         | 8 ms                                      |
| Board 6  | 267 ms                             | 1 ms                                          | 583 ms                         | 4 ms                                      |
| Board 7  | 270 ms                             | 2 ms                                          | 559 ms                         | 4 ms                                      |
| Board 8  | 11764 ms                           | 14 ms                                         | 9298 ms                        | 20 ms                                     |
| Board 9  | 1649 ms                            | 6 ms                                          | 508 ms                         | 4 ms                                      |
| Board 10 | ?                                  | 2586 ms                                       | ?                              | 977 ms                                    |
