function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.findUserByUsername = findUserByUsername;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.url = '/api/user';
    this.loginUrl = '/api/login';
    this.registerUrl = '/api/register';
    this.findByUrl='/api/findby';
    var self = this;

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function (response) {
                return response.json();
            });
    }

    function findUserByUsername(username) {
        return fetch(self.findByUrl + '/username/' + username)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            'method': 'DELETE'
        });
    }

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'PUT',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function (response) {
                if (response.bodyUsed) {
                    return response.json();
                } else {
                    return null;
                }
            });
    }

    function register(user) {
        return fetch(self.registerUrl, {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    function login(username, password) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

}