export enum CardColor {
  Hearts = "h",
  Diamonds = "r",
  Clubs = "k",
  Spades = "s"
};

export type PokerCard = {
  id: number
  value: string
  color: CardColor
};

export type PokerHand = {
  id: number,
  cards: PokerCard[],
};
