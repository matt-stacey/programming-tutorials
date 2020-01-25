secret_word = "giraffe"
guess = ""
tries = 3

while guess.lower() != secret_word and tries > 0:
    guess = input(f"input the secret word ({tries} tries remaining): ")
    tries -= 1

if guess.lower() == secret_word:
    print("you guessed the secret word")
else:
    print("you didn't guess the secret word")