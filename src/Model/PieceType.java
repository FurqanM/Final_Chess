package Model;



public enum PieceType
{
	PAWN {
		@Override
		public void IsWhite(boolean white)
		{
			//return new PAWN(true);
			
		}
	}, BISHOP {
		@Override
		public void IsWhite(boolean white)
		{
			// TODO Auto-generated method stub
			
		}
	}, ROOK {
		@Override
		public void IsWhite(boolean white)
		{
			// TODO Auto-generated method stub
			
		}
	}, KNIGHT {
		@Override
		public void IsWhite(boolean white)
		{
			// TODO Auto-generated method stub
			
		}
	}, QUEEN {
		@Override
		public void IsWhite(boolean white)
		{
			// TODO Auto-generated method stub
			
		}
	}, KING {
		@Override
		public void IsWhite(boolean white)
		{
			// TODO Auto-generated method stub
			
		}
	},
	NOPIECE {
		@Override
		public void IsWhite(boolean white)
		{
			// TODO Auto-generated method stub
			
		}
	};
	
	
	
	
	public abstract void IsWhite(boolean white); 
	
}