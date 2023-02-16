import sys
from deep_translator import GoogleTranslator

source = sys.argv[1]
target = sys.argv[2]
text = sys.argv[3]

test = GoogleTranslator(source=source, target=target).translate(text);
print(test)
