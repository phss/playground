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

end
