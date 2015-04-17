# SimpleDatabase

Data Commands


    SET name value – Set the variable name to the value value. Neither variable names nor values will contain spaces.

    GET name – Print out the value of the variable name, or NULL if that variable is not set.

    UNSET name – Unset the variable name, making it just like that variable was never set.

    NUMEQUALTO value – Print out the number of variables that are currently set to value. If no variables equal that value, print 0.

    END – Exit the program. The program will always receive this as its last command.
    
    
Transaction Commands



    BEGIN – Open a new transaction block. Transaction blocks can be nested; a BEGIN can be issued inside of an existing block.

    ROLLBACK – Undo all of the commands issued in the most recent transaction block, and close the block. Print nothing if successful, or print NO TRANSACTION if no transaction is in progress.

    COMMIT – Close all open transaction blocks, permanently applying the changes made in them. Print nothing if successful, or print NO TRANSACTION if no transaction is in progress.
    
Performance Considerations



    The most common operations are GET, SET, UNSET, and NUMEQUALTO. All of these commands should have an expected worst-case runtime of O(log N) or better, where N is the total number of variables stored in the database.

    The vast majority of transactions will only update a small number of variables. Accordingly, the solution should be efficient about how much memory each transaction uses.




