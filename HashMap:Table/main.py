from HashTableClass import HashTableClass

if __name__ == '__main__':
    arr = HashTableClass(100)
    arr.insert("farzad", 5)
    arr.insert("alex", 5)
    arr.insert("jeff", 5)
    print(arr.hash_table)
    print(arr.get("farzad"))
    arr.delete("alex")
    print(arr.hash_table)

