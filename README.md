# ICS 4U0 — Lesson 5: String Methods and Math Functions

## Exercise

You're building a quick profile card for a player, from their name and a couple of scores.

Everything you need is from Lesson 5:

- **substring** and **toUpperCase**/**toLowerCase** to build a username,
- **length** to measure the player's full name, and
- **Math.abs**, **Math.sqrt**, and **Math.pow** to compute three stats.

- Read the player's first name and last name (one per line, `nextLine()`), then their score and their rival's score (`nextLine()`, converted with `Integer.parseInt()`).
- Build `username`: the first letter of the first name, uppercased, followed by the last name, all lowercase.
- Compute `nameLength` as the combined length of the first and last names.
- Compute `scoreDifference` as the absolute value of `score - rivalScore`.
- Compute `powerRating` as the square root of `score`.
- Compute `bonusPoints` as `scoreDifference` raised to the power of `2`.

---

## Input

The player's first name, last name, score, and rival's score, one per line:

| Line | Value | Type |
|------|-------|------|
| 1 | First name | word (no spaces) |
| 2 | Last name | word (no spaces) |
| 3 | Score | whole number |
| 4 | Rival's score | whole number |

Example input:

```
diego
RAMIREZ
81
65
```

---

## Output

Exactly five lines:

```
Username: <username>
Full name length: <count>
Score difference: <amount>
Power rating: <amount to 2 decimals>
Bonus points: <amount to 2 decimals>
```

For the example input above, your program must print **exactly**:

```
Username: Dramirez
Full name length: 12
Score difference: 16
Power rating: 9.00
Bonus points: 256.00
```

Every space and colon is compared. `Username:Dramirez` and `Username: Dramirez` are not the same answer.

---

## Testing

- Test your code yourself first.
- Open the **Testing** panel from the sidebar (flask icon) and click ▶ **Run Tests**.

A green check next to a test means it passed; a red X means it failed and will show you the expected vs. actual output.
