function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    /*this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.login = login();*/
    this.url = 'http://localhost:8080/api/user';
    /*this.login = 'http://localhost:8080/api/login';*/
    var self = this;

    function findAllUsers() {
        return fetch(self.url)
            .then(function(response){
                return response.json();
            });
    }
}