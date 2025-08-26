# Chuck Norris Cipher — Java CLI

A small, console-based encoder/decoder that implements the **Chuck Norris unary cipher** and a simple text UI. This project focuses on clean structure, input validation, and correctness rather than size or complexity.

---

## What this app does

* Repeatedly prompts for an operation: `encode`, `decode`, or `exit`.
* **Encode**: converts a text line into the Chuck Norris unary format.
* **Decode**: converts a Chuck Norris unary string back to text.
* **Exit**: prints `Bye!` and terminates.

All I/O is line-based and matches the task specification exactly.

---

## How the cipher works

### 1) Encoding (text → unary)

The encoding pipeline:

1. **Text → 7-bit binary**
   Each character is converted to its **7-bit ASCII** code and left-padded with zeros.
   Example:

   * `'A'` → `1000001`
   * `'!'` → `0100001`

2. **Binary → Chuck Norris unary**
   The binary string is compressed as runs of identical bits:

   * A run of `1`s is encoded as: `0 <spaces> zeros`, written as `0` then a block of `0`s whose **count equals the run length**.
   * A run of `0`s is encoded as: `00 <spaces> zeros`, written as `00` then a block of `0`s whose **count equals the run length**.
   * Blocks are separated by single spaces.

   Example for binary `1000011`:

   * `1` (len 1) → `0 0`
   * `0000` (len 4) → `00 0000`
   * `11` (len 2) → `0 00`
     Final: `0 0 00 0000 0 00`

**Time complexity:** O(n), single pass over the input.

---

### 2) Decoding (unary → text)

The decoding pipeline:

1. **Normalize & validate the encoded input**

   * Trim whitespace.
   * Reject if it contains characters other than `0` or space.
   * Split on one-or-more spaces (`\\s+`) to tolerate accidental extra spaces.
   * The number of tokens must be **even**, because they come in pairs: a **header** and a **count block**.

2. **Pairs → binary**

   * For every pair:

     * Header must be `0` (means a run of `1`s) or `00` (means a run of `0`s).
     * The length of the count block gives the run length.
     * Append that many `1`s or `0`s to a binary accumulator.
   * After all pairs are processed, the **final binary length must be a multiple of 7**.

3. **Binary → text**

   * Read the binary string 7 bits at a time.
   * Convert each 7-bit group to an ASCII character and append.

**Invalid encoded strings** trigger the message:

```
Encoded string is not valid.
```

Validation covers:

* Characters other than `0` and spaces,
* Odd number of blocks,
* Header not `0`/`00`,
* Final binary length not a multiple of 7.

**Time complexity:** O(n), single pass to build binary and single pass to convert to text.

---

## Project structure

```
org/example/
├─ Main.java        // Console UI: prompts, reads input, prints output, loop
└─ ChuckCodec.java  // Pure encoding/decoding logic (no I/O)
```

* `Main` holds user prompts and routes to the codec.
* `ChuckCodec` exposes:

  * `encode(String plain) : String`
  * `tryDecode(String unary, StringBuilder out) : boolean`
    (returns `true` on success and writes the decoded text into `out`)

This separation keeps logic testable and the UI straightforward.

---

## Sample session

```
Please input operation (encode/decode/exit):
> encode
Input string:
> Hey!
Encoded string:
0 0 00 00 0 0 00 000 0 00 00 00 0 0 00 0 0 00000 00 00 0 0 00 0 0 0 00 0000 0 0

Please input operation (encode/decode/exit):
> decode
Input encoded string:
> 0 0 00 00 0 0 00 000 0 00 00 00 0 0 00 0 0 00000 00 00 0 0 00 0 0 0 00 0000 0 0
Decoded string:
Hey!

Please input operation (encode/decode/exit):
> exit
Bye!
```

---

## How to run

These steps work on any machine with a JDK installed and **don’t rely on any Gradle plugin configuration**.

### 1) Build with the Gradle Wrapper

```bash
# macOS / Linux
./gradlew clean build

# Windows
.\gradlew.bat clean build
```

### 2) Run the app

```bash
# macOS / Linux / Windows (same command)
java -cp build/classes/java/main org.example.Main
```

You’ll get an interactive prompt. Type `encode`, `decode`, or `exit` when asked.

> Tip: If you change the package or main class name, update the class in the `java -cp ... <MainClass>` command accordingly.

### (Optional) If the `run` task is configured

If your `build.gradle` applies the Application plugin and sets the main class, you can also run:

```bash
# macOS / Linux
./gradlew run

# Windows
.\gradlew.bat run
```

If `./gradlew run` doesn’t accept keyboard input in your environment, use the guaranteed method above (`java -cp ...`).

---

## Skills demonstrated

* **Core Java**

  * `Scanner` for console I/O
  * `StringBuilder` for efficient string accumulation
  * `switch` control flow and clear constants for messages
  * Regex for lightweight validation (`matches`, `split("\\s+")`)

* **Clean code & design**

  * **Separation of concerns:** UI in `Main`, pure logic in `ChuckCodec`
  * **Pure functions** for encoding/decoding; no side effects or I/O inside codec
  * **Spec-driven validation** with explicit checks and clear error messages
  * Robust tokenization: tolerant of extra spaces without loosening the spec

* **Problem solving**

  * Understanding of **7-bit ASCII** and bitwise representation
  * **Run-length encoding** concept for unary transformation
  * Linear-time algorithms with simple data structures

This is a **small** project by design, focused on correctness, clarity, and tidy structure rather than breadth.


