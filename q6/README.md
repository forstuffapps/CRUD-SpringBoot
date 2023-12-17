# Java Final Project

# Vishal Reddy Guda : C0869346


## Main Source Classes
## HitCounter
This is for Page counter
contains a variable **count** to calculate the count
Costructor
**add_Page_Hit()** increments the count

## HitCounterConfig
Configuration Bean for Dependency injection

## Project_Application
To run the Springbot application

## User
creates a table with name = **user**
Contains the following memeber
**Id** -> Auto generates
**email** -> should not be null and requires the **email** format validated with the annotations
**name** -> not null and minimum 2 characters and maximum 40
**age** -> minimum is 18
**occupation** -> not null and minimum 2 characters and maximum 40

## UserCache
**users_list** -> variable to store users data
**UserCache** constructor for initialising the empty list
**storeUser** -> stores user 
**getAllUsers** -> returns the user list

## UserCacheConfig
Configuration Bean for Dependency injection

## UserController
This is our main controller file
1. **User_DB** this is **Autowired** 
2. **hitCounter** this too
3. **userForm** with **@GetMapping("/")** returns the view **home**
4. **createUser** with **@PostMapping("/createuser")** returns the view **home** if it has errors or else saves the instance to database and returns the view **saved**
5. **listAllUsers** with **@GetMapping("/allusers")** returns the view **view**
6. **listAllUsersbyAge** with **@GetMapping("/sortage")** returns the view **view** with the user list sorted in age
7. **deleteuserbyid** with **@GetMapping("/delete/{id}")** returns the view **view** after deleting the instance with the id
8. **getHitCount** with **@GetMapping("/getHitCount")** returns the value **pagecount**


## UserRepository
has the methods 
**deleteById** -> to delete the instace with the id
**findById** -> to find the instance with the Id
