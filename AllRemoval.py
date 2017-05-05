import sys  

input = open(sys.argv[1],"r") #, encoding='utf-8')

add = ""

line = input.readline()
maxlength = 0
names = line.split(".")
filename = names[0] + ".conllu"
#firstPass = True

for line in input:
	#if firstPass == True:
	#	firstPass = False
	#	filename = line
	#	print (filename)
	if ".conllu" in line:
		language = open(filename, "r")
		output = open(filename + ".1", "w")
		iterator = language.readline()
		for iterator in language:
			words = iterator.split("\t")
			if "#" in words[0]:
				add += iterator
			elif "-" in words[0]:
				add += iterator
			elif "." in words[0]:
				nums = words[0].split(".")
				if int(nums[0]) <= maxlength:
					add += iterator
				else:
					add = ""
			elif "\n" in words[0]:
				add += iterator
				output.write(add)
				add = ""
			elif int(words[0]) <= maxlength:
				add += iterator
			elif int(words[0]) > maxlength:
				add = ""
			else:
				add += iterator
				output.write(add)
				add = ""
		maxlength = 0
		names = line.split(".")
		filename = names[0] + ".conllu"
	else:
		nums = line.split("\t")
		if "." in  nums[0]:
			thing = 0
		elif int(nums[0]) > maxlength:
			maxlength = int(nums[0])
language = open(filename, "r")
output = open(filename + ".1", "w")
iterator = language.readline()
for iterator in language:
	words = iterator.split("\t")
	if "#" in words[0]:
		add += iterator
	elif "-" in words[0]:
		add += iterator
	elif "." in words[0]:
		nums = words[0].split(".")
		if int(nums[0]) <= maxlength:
			add += iterator
		else:
			add = ""
	elif "\n" in words[0]:
		add += iterator
		output.write(add)
		add = ""
	elif int(words[0]) <= maxlength:
		add += iterator
	elif int(words[0]) > maxlength:
		add = ""
	else:
		add += iterator
		output.write(add)
		add = ""
	