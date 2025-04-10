const express = require('express');
const { exec } = require('child_process');
const fs = require('fs');
const app = express();

// Middleware to parse JSON payloads
app.use(express.json());

// Endpoint to execute Java code
app.post('/api/run', async (req, res) => {
    const { code } = req.body;

    if (!code) {
        return res.status(400).json({ error: 'No code provided' });
    }

    // Save the code to a temporary file
    const filePath = '/tmp/Main.java';
    fs.writeFileSync(filePath, code);

    // Compile and run the Java code with Selenium dependencies
    exec(`javac -cp /home/runner/${process.env.REPL_SLUG}/src/libs/selenium-server-4.10.0.jar ${filePath} && java -cp /home/runner/${process.env.REPL_SLUG}/src/libs/selenium-server-4.10.0.jar:/tmp Main`, (error, stdout, stderr) => {
        if (error) {
            return res.json({ output: stderr || 'Compilation failed' });
        }
        res.json({ output: stdout || 'No output' });
    });
});

// Start the server
app.listen(3001, () => console.log('Replit server running on port 3001'));
