const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {
    res.render('index');
})

router.get('/signup', (req, res) => {
    res.render('signup');
})

router.get('/resetpassword', (req, res) => {
    res.render('resetpassword');
})

module.exports = router;