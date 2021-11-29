const express = require("express");
const path = require('path');
const mysql = require("mysql");
const dotenv = require('dotenv');

dotenv.config({ path: './.env'});


const app = express();

const db = mysql.createConnection({
    host: process.env.DATABASE_HOST, // ip address of the server so eustis3.eecs.ucf.edu
    user: process.env.DATABASE_USER,
    password: process.env.DATABASE_PASSWORD,
    database: process.env.DATABASE // name of database
});

const publicFrontEndDirectory = path.join(__dirname, './public');
app.use(express.static(publicFrontEndDirectory));

// Parse URL encoded bodies
app.use(express.urlencoded({ extended: false}));

// Parse JSON values
app.use(express.json());

app.set('view engine', 'hbs');

db.connect( (error) => {
    if(error){
        console.log(error)
    }
    else{
        console.log("MYSQL Succesfully Connected")
    }
})

// Define Routes
app.use('/', require('./routes/pages'))
app.use('/auth', require('./routes/auth'));


app.listen(7000, () => {
    console.log("Server started on Port 7000")
});