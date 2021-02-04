import sys
import os

paths = {}

root = "C:/Program Files/Scripts/"
home = os.getcwd()

def readDirs():

	global paths

	os.chdir(root)

	if os.path.exists('dir.txt'):

		with open('dir.txt') as f:

			i = 1
			for path in f.readlines():
				# print(path)

				path = path.strip()
				if os.path.isdir(path):
					base = os.path.basename(path).lower()
					paths[base] = path.lower()

	else:

		f = open('dir.txt', 'w+')
		f.close()

	os.chdir(home)

def addDirs():
	print("Adding directories... ")
	print()
	newpath = input("Please enter the directory you would like to add: ")

	if os.path.exists(newpath):
		appendToDir(newpath)

	else: 
		print(f"[ERR] Could find directory {newpath}")
		os.system('pause')

def appendToDir(path):

	global root, home
	home = os.getcwd()
	os.chdir(root)

	if os.path.exists('dir.txt'):
		
		f = open('dir.txt', 'a+')
		f.write("\n"+ path)
		f.close()
		print(f"Succesfully added new file: {path}")
		print(f"Enter goto {os.path.basename(path)} to change directories")

	else:

		f = open('dir.txt', 'w+')
		f.write(path)
		f.close()
		print(f"Succesfully added new file: {path}")
		print(f"Enter goto {os.path.basename(path)} to change directories")

	os.chdir(home)

def printDirs():
	
	global paths
	print("The following are the list of directories in the registry: ")

	banner = "="*50 
	print(banner)
	print()

	for key in paths.keys():
		print(f"{key}: {paths[key]}")

	print()
	print(banner)
	os.system('pause')

def start(command):

	readDirs()
	command = command[0].lower()
	c = command[:2]
	if (c == '--') :

		command = command[2:]
		if (command == 'view'):
			printDirs()

		elif (command == 'add'):
			addDirs()

		else:
			print("Unknown command. Acceptable flags include [add, view]")
			os.system("pause")


	else:
		dest = command
		if (dest in paths.keys()):
			destdir = paths[dest]
			return destdir

		else:
			print("Path/folder was not found in registry")
			os.system("pause")

def main():
	if (len(sys.argv) > 1):
		return start(sys.argv[1:])

if __name__ == "__main__":
	main()