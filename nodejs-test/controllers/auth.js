const mysql = require("mysql");
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');

const db = mysql.createConnection({
    host: process.env.DATABASE_HOST, // ip address of the server so eustis3.eecs.ucf.edu
    user: process.env.DATABASE_USER,
    password: process.env.DATABASE_PASSWORD,
    database: process.env.DATABASE // name of database
});

exports.signup = (req, res) => {
    console.log(req.body);
    /*
        const firstName = req.body.firstname;
        const lastName = req.body.lastname;
        const userName = req.body.username;
        const userEmail = req.body.email;
        const userPassword = req.body.password;
        const userPasswordConfirm = req.body.passwordConfirm;
        all of this is equivalent to the line below
    */

    const { firstname, lastname, username, email, password, passwordConfirm } = req.body;

    db.query('SELECT email FROM users WHERE email = ?', 
                [email], async (error, results) => {
                    if(error){
                        console.log(error);
                    }

                    if(results.length > 0){
                        return res.render('signup', {
                        message: 'That email is already in use!'
                    })
                    }

                    else if(password !== passwordConfirm){
                        return res.render('signup', {
                        message: 'Passwords do not match!'
                    })
                    }

                    let hashedPassword = await bcrypt.hash(password, 2);
                    console.log(hashedPassword);

                    db.query('INSERT INTO users SET ?',
                     { firstname: firstname,
                       lastname: lastname,
                       username: username,
                       email: email,
                       password: hashedPassword }, (error, results) => {
                        if(error){
                            console.log(error);
                        } else {
                            console.log(results)
                            return res.render('signup', {
                            message: 'Signup successful!'
                            })
                        } 
                       })
                });

}