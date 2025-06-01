export enum CardColor {
  Hearts = "h",
  Diamonds = "r",
  Clubs = "k",
  Spades = "s"
};

export enum HandCategory {
    HighCard = "HighCard",
    OnePair = "OnePair",
    TwoPair = "TwoPair",
    ThreeOfAKind = "ThreeOfAKind",
    Flush = "Flush",
    FullHouse = "FullHouse",
    FourOfAKind = "FourOfAKind",
    StraightFlush = "StraightFlush"
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

export type PokerAnalysis = {
  handId: number,
  cards: PokerCard[],
  category: HandCategory,
  highestInCategory: number
}
