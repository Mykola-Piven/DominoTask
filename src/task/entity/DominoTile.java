package task.entity;

public class DominoTile {

	private byte num1;
	private byte num2;

	public DominoTile(byte num1, byte num2) {
		this.num1 = num1;
		this.num2 = num2;
	}

	public byte getNum1() {
		return num1;
	}

	public byte getNum2() {
		return num2;
	}

	public DominoTile getReverseTile() {
		return new DominoTile(num2, num1);
	}

	@Override
	public String toString() {
		return "[" + num1 + "|" + num2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num1;
		result = prime * result + num2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DominoTile other = (DominoTile) obj;
		if (num1 != other.num1)
			return false;
		if (num2 != other.num2)
			return false;
		return true;
	}

}
