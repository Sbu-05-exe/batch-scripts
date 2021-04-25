import os
import sys

def main():
	if (len(sys.argv) == 2):
		os.system(f"terminal {sys.argv[1]}")

	else:
		cd = os.getcwd()
		cd_base = os.path.basename(cd)
		os.system(f"terminal {cd_base}")

if __name__ == "__main__":
	main();