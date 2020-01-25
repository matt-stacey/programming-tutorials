# translates a phrase to the giraffe language, where all vowels are replaced with g's

def translate(phrase):
    vowels = "aeiou"
    translation = ""
    for letter in phrase:
        if letter in vowels:
            translation += "g"
        elif letter in vowels.upper():
            translation += "G"
        else:
            translation += letter
    return translation

print(translate(input("Enter a phrase: ")))