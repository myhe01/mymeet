const express = require('express');
const authController = require('../controllers/auth');
const router = express.Router();

// auth/signup
router.post('/signup', authController.signup )
// create a controller that handles input
    


module.exports = router;