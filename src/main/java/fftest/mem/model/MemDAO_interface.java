package fftest.mem.model;

public interface MemDAO_interface {
	public void insert(MemVO memVO);

	public void update(MemVO memVO);

	public void delete(Integer memId);

	public MemVO findByPrimaryKey(Integer memId);
}
