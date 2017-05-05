import sys  

input = open(sys.argv[1],"r") #, encoding='utf-8')
output = open(sys.argv[2],"w") #, encoding='utf-8')
maxlength = int(sys.argv[3])

add = ""

for line in input:
	words = line.split("\t")
	if "#" in words[0]:
		add += line
	elif "-" in words[0]:
		add += line
	elif "." in words[0]:
		nums = words[0].split(".")
		if int(nums[0]) <= maxlength:
			add += line
		else:
			add = ""
	elif "\n" in words[0]:
		add += line
		output.write(add)
		#output.write("")
		add = ""
	elif int(words[0]) <= maxlength:
		add += line
	elif int(words[0]) > maxlength:
		add = ""
	else:
		add += line
		output.write(add)
		#output.write("")
		add = ""