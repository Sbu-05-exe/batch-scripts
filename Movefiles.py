import os

def moveFiles(search, folder, directory):	

	cd = os.getcwd()
	os.chdir(directory)

	if not(os.path.isdir(directory)):
		return f"Could not find searching directory: {directory}"

	if not(os.path.isdir(folder)):
		return f"Could not find destination folder:  {folder} "

	results = os.listdir()
	results = [file for file in results if search in file]

	print(f"({len(results)}) matches for {search}")

	for file in results:
		os.system(f'move "{file}" "{folder}"')

	os.chdir(cd)

	return f"Successfuly moved all {search} files"

def main():

	directory = input("Enter directory of folder to search for files: ")
	search = input("What files are you searching for in this folder: ")
	folder = input("Enter destination folder: ")

	if (directory == ""):
		directory = "C:/Users/Itsbu/Downloads/"

	print(moveFiles(search, folder, directory))


if __name__ == "__main__":
	main()