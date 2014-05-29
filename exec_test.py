import subprocess

subprocess.call(["javac", "Node.java", "Rope.java", "Test.java"])
subprocess.call(["java", "Test"])