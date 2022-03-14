class HashTableClass:

    # Constructor for the HashTableClass
    def __init__(self, hash_size):
        self.hash_table = [[] for i in range(hash_size)]
        self.size = 0

    # Calculates hash value for the given key
    def hash_function(self, key):
        hash_value = 0
        for char in key:
            hash_value += ord(char)
        return hash_value % len(self.hash_table)

    # Inserts the given key-value pair to the hash map
    def insert(self, key, value):
        position = self.hash_function(key)
        found = False
        for idx, element in enumerate(self.hash_table[position]):
            if len(element) == 2 and element[0] == key:
                found = True
                raise Exception("This key value already exists in the hash table")

        if not found:
            self.hash_table[position].append((key, value))
        self.size += 1

    # Gets the value for the given key
    def get(self, key):
        position = self.hash_function(key)
        for element in self.hash_table[position]:
            if element[0] == key:
                return element[1]
        return self.hash_table[position]

    # Removes the item with given key
    def delete(self, key):
        position = self.hash_function(key)
        for idx, element in enumerate(self.hash_table[position]):
            if element[0] == key:
                self.hash_table[position][idx] = None
        self.hash_table[position] = []
        self.size -= 1
