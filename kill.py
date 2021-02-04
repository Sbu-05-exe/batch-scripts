import sys, os

def killtasks(apps):

	for app in apps:
		os.system(f"taskkill /f /im {app}.exe")

def main():

	if (len(sys.argv) < 2):
		print("Usage: kill nameofprogram(s) ")

	killtasks(sys.argv[1:]);


if __name__ == "__main__":
	main()