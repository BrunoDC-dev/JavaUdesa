function findNextSquare(sq) {
    // Return the next square if sq is a perfect square, -1 otherwise
    let root = Math.sqrt(sq);
    if (Number.isInteger(root)) {
      return (root + 1) * (root + 1);
    }
    
    return -1;
  }