#!`/home/bala/anaconda3/bin/python
import fire
from bs4 import BeautifulSoup
from bs4.element import Tag, NavigableString
import json
import pickle

class Pickled():
    def __init__(self, fname, obj=None):
        self.obj = obj
        self.fname = fname

    def __enter__(self):
        try:
            with open(self.fname, "rb") as f:
                self.obj = pickle.load(f)
        except FileNotFoundError as e:   print(e)
        except Exception as e:           print(e)
        return self.obj
    
    def __exit__(self,a,b,v):
        with open(self.fname, "wb") as f:
            f.write(pickle.dumps(self.obj))

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

class Project():
    def __init__(self):
        self.questions = []
        self.state_dict = {}

    def __repr__(self):
        #return f"{self.questions}"
        return f"{self.transform()}"
    
    def clear(self):
        self.questions.clear()
        self.state_dict.clear()
    
    def transform(self):
        return { q.ques_key: q.ans for q in self.questions }

class Question(object):
    def __init__(self, q, key, silent=False):
        self.ques_key = key
        self.ques = q
        if silent:
            self.ans = ""
        else: 
            self.ans = input(self.ques)

    def __repr__(self):
        return f"Q: {self.ques} Ans: {self.ans}"

class SpringBootApp(object):
    """A simple SpringBootApp class."""
    def __init__(self):
        self.path = "./"
        self.project = None

    def setbasepath(self, path="./"):
        """Set base folder to generate source code"""
        self.path = path
        print(f"path set to {path}")

    def getbasepath(self):
        """Returns the current base folder path"""
        return self.path

    def init(self):
        with Pickled("project.pkl", Project()) as p:
            self.project = p
            qs = [
                Question("Choose a project name ", "project_name"),
                Question("Choose spring boot version ", "spring_version")
            ]
            self.project.questions.extend(qs)
            
    
    def project_obj(self):
        with Pickled("project.pkl") as p:
            print(p)

    def destroy(self):
        with Pickled("project.pkl") as p:
            p.clear()
            print("Pickle file destroyed.")

if __name__ == '__main__':

    Util.load_xml("./template/pom.xml")
    fire.Fire(SpringBootApp)