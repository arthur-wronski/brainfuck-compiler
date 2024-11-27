# Brainfuck Interpreter 🧠⚡

## My Coding Journey 🚀

After my incredible implementation of the WAR card game in Java, I decided to level up and build a Brainfuck interpreter. Why? Because I hate myself ️‍🔥

## The Brainfuck Breakdown 🔍

This project is a love letter to all the programmers who enjoy overcomplicating simple tasks as well as diving into things way beyond their understanding

### Project Components 

- **Lexer**: The token bouncer 🕴️
  - Creates a queue of tokens
  - Bounces out any syntax that's not in the Brainfuck alphabet
  - Basically, if it's not `><!+-.,[]`, it's not getting into this party

- **Parser**: The recursion ninja 🥷
  - Goes through the token queue recursively
  - Ensures no mismatched loops
  - Could I have made a parse tree? Sure. Did I? Nah. Overkill for now.

- **Interpreter**: The code executor 🔫
  - Translates tokens into actual operations
  - Manages the Turing machine-like tape
  - Makes the magic happen

- **TuringMachine**: The memory maestro 🎹
  - Manages the byte tape
  - Handles pointer movement
  - Executes basic operations

## Future Roadmap 🗺️

Todo list (aka my procrastination playground):
- Code optimization (bundling operations like 10 consequent increments)
- Potentially more specific exceptions
- Maybe a compiler for a "real" language

## How I Did It 🤓

- Zero AI help for implementation
- Shamelessly used AI for tests (because fuck that) and README.md (made a vow to never learn Markdown)

## Why Brainfuck? 

Because I'm quirky.
