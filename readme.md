1. Added exception classes - Now `Image` throws `InvalidImagePathArgument` exception, providing more readable error handling.
  
2. Implemented verbose exceptions in the interface layer to give users and programmers finer control over input validation, which is a significant aspect of this program. This was accomplished using base exceptions like `ParamException` that can be inherited and customized (and potentially expanded with more exceptions adhering to the open/closed principle).

3. Utilized a dictionary-based factory pattern in the shell to eliminate repetitive if/switch statements. All parameters implement the same `IParamHandler` interface. This approach also supports the open/closed principle as new parameters can be added without modifying existing code.