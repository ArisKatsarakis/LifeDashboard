# Description
The user sends a Json that contains the IncomeSource Type. 
The system returns the IncomeSource type and their responding resources.
# Basic Flow
    - The user sents a request with the valid json.
        - The system fetches the income source type.
        - The system counts the sum of the incomes for this IncomeSource.
        - The system returns the data to the user.