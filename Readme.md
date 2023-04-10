# **Notes**
    - Implementing Spring security modlue 
    - Encrypting the passwords in DB unsing base64 
    - Introduced a new method called register to create new admin
    - deprecated the login endpoint and replace it with /authenticate endpoint [in real life will remove it]
    - update the postman file with the new version to be able to test, you can find it under src/main/resources

# **Test procedure**
    - you can authenticate with the same credentials admin/p@ssw0rd as i updated the password in data.sql with the encrypted one
    - create new admin using /register endpoint
    - copy the Jwt and put it in Authorization part and test the user collection of list and adding userss