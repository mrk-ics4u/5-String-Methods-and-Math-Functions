# Lesson 5 - String Methods and Math Functions

Strings and numbers aren't new ideas: Python had both. What's new is Java's syntax for working with them, and two behaviors that don't exist in Python at all: reference comparison with `==`, and a math library that's all static methods instead of standalone functions.

---

## 1. Concatenation Isn't Always Concatenation

Python refuses to mix strings and numbers with `+`.

```python
# Python
print("Total: " + 3 + 2)   # TypeError: can only concatenate str to str
```

Java allows it, which is more dangerous than helpful. Once `+` touches a `String`, everything after it is evaluated left to right and converted to a `String` one term at a time.

```java
System.out.println("Total: " + 3 + 2);   // "Total: 32", not "Total: 5"
System.out.println("Total: " + (3 + 2)); // "Total: 5" -- parentheses force the addition first
```

Strings are immutable in Java, exactly as in Python: no method ever changes the `String` it's called on. `word.toUpperCase()` returns a new `String`; `word` itself is untouched unless you reassign it.

```java
String word = "hello";
word.toUpperCase();
System.out.println(word);              // still "hello"
word = word.toUpperCase();
System.out.println(word);              // now "HELLO"
```

---

## 2. String Methods

Python's string methods and Java's do the same jobs; the names and call syntax differ.

| Python | Java | Returns |
|---|---|---|
| `len(s)` | `s.length()` | number of characters |
| `s[i]` | `s.charAt(i)` | the character at index `i` |
| `s[a:b]` | `s.substring(a, b)` | characters from index `a` up to, not including, `b` |
| `s[a:]` | `s.substring(a)` | characters from index `a` to the end |

```java
String word = "Ontario";
System.out.println(word.length());        // 7
System.out.println(word.charAt(0));        // 'O'
System.out.println(word.substring(0, 3));  // "Ont"
System.out.println(word.substring(3));     // "ario"
```

`charAt` returns a `char`, a single-character primitive type with no equivalent in Python (Python has no separate character type; `s[i]` is a length-1 string). Indexes run from `0` to `length() - 1`, same as Python; asking for an index outside that range throws `StringIndexOutOfBoundsException`, Java's version of Python's `IndexError`.

Building a new string out of pieces of an old one is the standard way to "edit" an immutable string. Swapping the characters at positions `i` and `j`:

```java
String word = "cta";
int i = 0, j = 2;
String swapped = word.substring(0, i) + word.charAt(j)
                + word.substring(i + 1, j) + word.charAt(i)
                + word.substring(j + 1);
System.out.println(swapped);   // "atc"
```

---

## 3. Comparing Strings: `equals`, Not `==`

In Python, `==` compares string contents. In Java, `==` on objects compares whether two variables point to the exact same object in memory, not whether the objects hold equal values.

```java
String a = "cat";
String b = "cat";
String c = new String("cat");

System.out.println(a == b);        // true -- string literals are reused, so this happens to work
System.out.println(a == c);        // false -- c is a distinct object with the same content
System.out.println(a.equals(c));   // true -- equals compares content, always
```

`a == b` being `true` is a trap, not a rule: it works here because the compiler reuses identical literals, and it silently breaks the moment a string comes from anywhere else, such as `Scanner` input.

```java
String typed = console.nextLine();      // user types "cat"
System.out.println(typed == "cat");     // false, even if they typed exactly "cat"
System.out.println(typed.equals("cat")); // true -- this is the check you want
```

**Always use `.equals()` to compare string content.** Use `.equalsIgnoreCase()` when case shouldn't matter.

For ordering rather than equality, use `compareTo`, which mirrors what `<` and `>` already do for strings in Python:

```java
System.out.println("apple".compareTo("banana"));  // negative: "apple" comes first alphabetically
System.out.println("banana".compareTo("apple"));  // positive: "banana" comes first alphabetically
System.out.println("apple".compareTo("apple"));   // 0: equal
```

Only the sign matters, not the exact number: negative, zero, or positive.

---

## 4. Case and Searching

```java
String s = "Ontario";
System.out.println(s.toUpperCase());     // "ONTARIO"
System.out.println(s.toLowerCase());     // "ontario"
System.out.println(s.indexOf("tar"));    // 2
System.out.println(s.indexOf("xyz"));    // -1, not found
```

Same jobs as Python's `.upper()`, `.lower()`, and `.find()`. `indexOf` matches `.find()` exactly, returning `-1` when nothing is found, unlike `.index()`, which raises an exception instead.

Counting occurrences combines `indexOf` with a loop, searching again from just past the last match:

```java
String sentence = "the cat sat on the mat";
int count = 0;
int pos = sentence.indexOf("at");
while (pos != -1) {
    count++;
    pos = sentence.indexOf("at", pos + 1);   // search again, starting after this match
}
System.out.println(count);   // 3
```

---

## 5. The Math Class

Python's math tools are split across a `math` module and built-ins (`abs()`, `**`, `math.sqrt()`, `random.random()`). Java puts the equivalents in one place, the `Math` class, and every one of them is a `static` method: called on the class name, never on an object.

| Python | Java |
|---|---|
| `abs(x)` | `Math.abs(x)` |
| `x ** y` | `Math.pow(x, y)` |
| `math.sqrt(x)` | `Math.sqrt(x)` |
| `random.random()` | `Math.random()` |

```java
System.out.println(Math.abs(-7));          // 7
System.out.println(Math.pow(2, 10));       // 1024.0 -- pow always returns a double
System.out.println(Math.sqrt(81));         // 9.0
System.out.println(Math.random());         // some double, 0.0 <= x < 1.0
```

`Math.random()` only ever gives a `double` in `[0.0, 1.0)`. Everything else, an integer in a range, a die roll, is built with arithmetic and casting on top of it. To get a random integer in `[min, max]` inclusive:

```java
int min = 1, max = 6;
int roll = (int) (Math.random() * (max - min + 1)) + min;
```

`Math.random() * (max - min + 1)` scales the range up to `[0.0, max - min + 1)`, casting to `int` truncates to a whole number in `[0, max - min]`, and adding `min` shifts it into `[min, max]`.

---

## Try It Yourself

Compile and run the companion file in this folder:

```bash
javac StringMathDemo.java
java StringMathDemo
```

Then work on the exercise in `PlayerProfile.java`.

---

## Course Expectations

### ICS 4U

Nothing is assessed directly from this lesson. Using existing subprograms such as `substring`, `abs`, and `random` was already assessed in ICS3U (A3.1, Subprograms, whose own examples name exactly these three); this lesson is the Java syntax for that skill. The `equals`/`compareTo` comparison content feeds ICS4U's own A1.3 (Data Types and Expressions: non-numeric comparisons), Strand A: Programming Concepts and Skills.

### AP Expectations

The following are expectations of the AP exam and will show up on the final exam:

- Creating a `String` object from a string literal or the `String` class constructor (Topic 1.15, String Manipulation, 1.15.A.1)
- The `String` class is part of `java.lang` and is available without an import (Topic 1.15, String Manipulation, 1.15.A.2)
- `String` objects are immutable; no method call changes the object it was called on (Topic 1.15, String Manipulation, 1.15.A.3)
- Concatenating strings with `+` or `+=`, including implicit conversion of a primitive value to a `String` (Topic 1.15, String Manipulation, 1.15.A.4)
- Concatenating a `String` with any object implicitly calls that object's `toString` method (Topic 1.15, String Manipulation, 1.15.A.5)
- Valid `String` indexes run from `0` to `length() - 1`; an out-of-range index throws `StringIndexOutOfBoundsException` (Topic 1.15, String Manipulation, 1.15.B.1)
- The required `String` methods: `length()`, `substring(int, int)`, `substring(int)`, `indexOf(String)`, `equals(Object)`, `compareTo(String)` (Topic 1.15, String Manipulation, 1.15.B.2)
- `equals` compares `String` objects only; comparing a `String` to a non-`String` object with `equals` is outside AP scope (Topic 1.15, String Manipulation, 1.15.B.2, exclusion statement)
- Extracting a single character as a one-character `String` with `substring(index, index + 1)` (Topic 1.15, String Manipulation, 1.15.B.3)
- The `Math` class methods `abs(int)`, `abs(double)`, `pow(double, double)`, `sqrt(double)`, and `random()` (Topic 1.11, Math Class, 1.11.A.1, 1.11.A.2)
- Using arithmetic and casting on `Math.random()` to produce a random `int` or `double` within a defined inclusive or exclusive range (Topic 1.11, Math Class, 1.11.A.3)
