class HashNode:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None


if __name__ == '__main__':
    x = HashNode(10, 7)
