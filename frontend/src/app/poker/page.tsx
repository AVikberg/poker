import { Suspense } from "react";
import PokerHandClient from "../ui/components/PokerHand/PokerHandClient";
import { BASE_URL, CreateNewHand } from "./actions";

export default function Poker() {
  const GetHands = async () => {
    const data = await fetch(`${BASE_URL}/hands`);
    return await data.json();
  }
  
  const hands = GetHands();

  return (
    <Suspense fallback={<div>Loading...</div>}>
      <div className="container px-4">
        <h1 className="text-2xl my-4">Poker</h1>
        <div className="flex items-center justify-between mb-2" style={{maxWidth: "50%"}}>
          <div className="mb-1">
            Poker hands:
          </div>
          <div className="justify-items-center">
            <button
              className="rounded-full bg-green-600 p-2"
              onClick={CreateNewHand}
            >
              Create new hand
            </button>
          </div>
          
        </div>
        <div className="flex gap-4">
          <div className="flex-none">
            <PokerHandClient hands={hands} />
          </div>
        </div>
      </div>
    </Suspense>
  );
}