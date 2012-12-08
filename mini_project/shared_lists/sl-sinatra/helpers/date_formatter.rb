module DateFormatter
  def day_and_month(date)
    date.strftime("%d/%m")
  end

  def week_day(date)
    date.strftime("%A")
  end

  def time_of(event)
    time = event.start_at.strftime("%k:%M")
    if event.end_at
      time += " to #{event.end_at.strftime('%k:%M')}"
    end
    return time
  end

  def week_start
    today = Date.today
    return (today - today.cwday + 1)
  end

  def week_end
    week_start + 6
  end

end
