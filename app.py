import fire
from bs4 import BeautifulSoup
from bs4.element import Tag, NavigableString
import json

class Util(object):

    @classmethod
    def traverse(cls, root, value_dct):
        if root == None or isinstance(root, NavigableString):
            return

        if hasattr(root, "name"):
            key, value = root.name,root.text.strip()
            value_dct[key] =value
            print(key, ":" ,value)

        for child in root.children:
            Util.traverse(child, value_dct)
        
        return value_dct

    @classmethod
    def load_xml(cls,fname):
        with open(fname) as f:
            soup = BeautifulSoup(f.read(), "lxml")
            v = {}
            v = Util.traverse(soup.find("project"), v)
            print(json.dumps(v, indent=4))

class Question(object):
    def __init__(self, q):
        self.ques = q 
        self.ans  = ""
        self.next = None

class SpringBootApp(object):
    """A simple SpringBootApp class."""
    def __init__(self):
        self.path = "./"

    def setbasepath(self, path="./"):
        """Set base folder to generate source code"""
        self.path = path
        print(f"path set to {path}")

    def getbasepath(self):
        """Returns the current base folder path"""
        return self.path

    def init(self):
        ans = Question("E")

if __name__ == '__main__':
    Util.load_xml("./template/pom.xml")
    fire.Fire(SpringBootApp)