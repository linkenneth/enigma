import sys

for arg in sys.argv[1:]:
    with open(arg) as inf:
        content = inf.read()
    with open("int.txt", 'w+') as outf:
        for c in content:
            if c != '\n':
                outf.write(str(ord(c) - ord("A")) + " ")
            else:
                outf.write('\n')

